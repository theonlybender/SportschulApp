package de.sportschulApp.shared;

import java.io.Serializable;



@SuppressWarnings("serial")
public class BankAccount implements Serializable{
	
	private String forename;
	private String surname;
	private String accountNumber;
	private String bankName;
	private String bankNumber;
	private int barcodeId;

	
	/**
	 * @param forename the forename to set
	 */
	public void setForename(String forename) {
		this.forename = forename;
	}
	/**
	 * @return the forename
	 */
	public String getForename() {
		return forename;
	}
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * @param bankNumber the bankNumber to set
	 */
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	/**
	 * @return the bankNumber
	 */
	public String getBankNumber() {
		return bankNumber;
	}
	/**
	 * @param barcodeId the barcodeId to set
	 */
	public void setBarcodeId(int barcodeId) {
		this.barcodeId = barcodeId;
	}
	/**
	 * @return the barcodeId
	 */
	public int getBarcodeId() {
		return barcodeId;
	}
	
	

}
