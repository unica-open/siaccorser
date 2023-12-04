/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siaccorser;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.csi.siac.siaccommon.util.JAXBUtility;
import it.csi.siac.siaccommon.util.log.LogUtil;
import it.csi.siac.siaccorser.model.Account;
import it.csi.siac.siaccorser.model.Ente;
import it.csi.siac.siaccorser.model.Entita;
import it.csi.siac.siaccorser.model.Operatore;
import it.csi.siac.siaccorser.model.Richiedente;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
	"/spring/corApplicationContext-test.xml",
	"/spring/corServiceClientContext-test.xml",
	"/spring/dozer-test.xml",
	"/spring/jpa-test.xml",
	"/spring/datasource-test.xml"})
public abstract class BaseJunit4TestCase extends TestCase {

	protected final LogUtil log = new LogUtil(getClass());
	private Properties accountProperties;

	@Autowired
	protected ApplicationContext applicationContext;
	
	
	@PostConstruct
	private void init() {
		final String methodName = "init";
		accountProperties = new Properties();
		InputStream is = null;
		try {
			is = getClass().getClassLoader().getResourceAsStream("./spring/account.properties");
			if(is != null) {
				try {
					accountProperties.load(is);
				} catch (IOException e) {
					// Non fare nulla
					log.error(methodName, "Errore nella lettura delle properties", e);
				}
			} else {
				log.error(methodName, "Properties non lette");
			}
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// Ignore
				}
			}
		}
		
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	/**
	 * Crea un'entit&agrave; con l'uid fornito
	 * @param clazz la classe dell'entit&agrave:
	 * @param uid l'uid dell'istanza
	 * @return l'entit&agrave; creata
	 */
	protected <T extends Entita> T create(Class<T> clazz, int uid) {
		T obj;
		try {
			obj = clazz.newInstance();
		} catch (Exception e) {
			throw new IllegalArgumentException("Impossibile creare un'istanza per la classe " + clazz + " con uid " + uid, e);
		}
		obj.setUid(uid);
		return obj;
	}
	
	/**
	 * Ottiene un richiedente di test dal file di properties.
	 *
	 * @param ambiente l'ambiente da usare (forn2, coll, ...)
	 * @param codiceEnte il codice dell'ente (coto, regp, crp, edisu...)
	 * 
	 * @return the richiedente test
	 */
	protected Richiedente getRichiedenteByProperties(String ambiente, String codiceEnte) {
		String codiceFiscale = accountProperties.getProperty(ambiente + "." + codiceEnte + ".codicefiscale");
		int uidAccount = Integer.parseInt(accountProperties.getProperty(ambiente + "." + codiceEnte + ".accountid"));
		int uidEnte = Integer.parseInt(accountProperties.getProperty(ambiente + "." + codiceEnte + ".enteproprietarioid"));
		
		Richiedente richiedente = new Richiedente();
		
		Operatore operatore = new Operatore();
		operatore.setCodiceFiscale(codiceFiscale);
		richiedente.setOperatore(operatore);
		
		richiedente.setAccount(create(Account.class, uidAccount));
		richiedente.getAccount().setNome("MioNomeFinto");
		richiedente.getAccount().setEnte(create(Ente.class, uidEnte));
		
		return richiedente;
	}
	
	
	/**
	 * Gets the test file bytes.
	 *
	 * @param fileName the file name
	 * @return the test file bytes
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static byte[] getTestFileBytes(String fileName) throws IOException {
		
		byte[] byteArray;
		java.io.File f = new java.io.File(fileName);
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		 
		try {
			baos = new ByteArrayOutputStream();
			fis = new FileInputStream(f);
			int b;
			while ((b = fis.read()) != -1){
				baos.write(b);
			}
			
			byteArray = baos.toByteArray();
			
		} finally {
			try {
				if(fis!=null) {
					fis.close();
				}
				fis = null;
				f = null;
				if(baos!=null){
					baos.close();
				}
				baos = null;
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return byteArray;
	}
	/**
	 * Crea un oggetto a partire da un file xml di test.
	 * 
	 * @param fileName ad esempio it/csi/siac/siacbilser/test/business/cassaeconomale/InserisceRichiestaEconomaleAnticipoSpese.xml 
	 * @return la request
	 * 
	 * @author Domenico
	 */
	public static <T> T getTestFileObject(Class<T> reqClass, String fileName){
		byte[] byteArray;
		String fullFileName = "docs/test/" + fileName;
		try {
			byteArray = getTestFileBytes(fullFileName);
		} catch (IOException e1) {
			e1.printStackTrace();
			fail("Impossibile ottenere il file di test: "+ fullFileName);
			return null;
		}
		String xml;
		try {
			xml = new String(byteArray, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			fail("Codifica UTF-8 non supporata. Impossile leggere il file di test: "+ fullFileName); //UTF-8 dovrebbe essere sempre supportato.
			return null;
		}
		
		return JAXBUtility.unmarshall(xml, reqClass);
	}

}
