package com.addressBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBook implements AddressBookIF {

	Scanner scannerObject = new Scanner(System.in);
	public Map<String, ContactPerson> contactList = new HashMap<String,ContactPerson>();
	public static HashMap<String, ArrayList<ContactPerson>> personByCity  = new HashMap<String, ArrayList<ContactPerson>>();
	public static HashMap<String, ArrayList<ContactPerson>> personByState = new HashMap<String, ArrayList<ContactPerson>>();
	public String addressBookName;
	
	public String getAddressBookName() {
		return addressBookName;
	}

	public void setAddressBookName(String addressBookName) {
		this.addressBookName = addressBookName;
	}
	
	public ArrayList<ContactPerson> getContact() {
		return new ArrayList<ContactPerson>(contactList.values());
	}

	@Override
	public void operation() {

		boolean moreChanges = true;
		do {

			System.out.println("\nChoose the operation you want to perform");
			System.out.println(
					"1.Add Contact to Address Book\n2.Edit Existing contact\n3.Display contact book\n4.Delete Contact\n5.Exit to Main Menu");

			switch (scannerObject.nextInt()) {
			case 1:
				addContact();
				break;
			case 2:
				editPerson();
				break;
			case 3:
				displayContents();
				break;
			case 4:
				deletePerson();
				break;
			case 5:
				moreChanges = false;
				System.out.println("Exiting Address Book: "+this.getAddressBookName()+" !");

			}

		} while (moreChanges);
	}

	@Override
	public void addContact() {

		ContactPerson person = new ContactPerson();

		System.out.println("Enter First Name: ");
		String firstName = scannerObject.next();
		
		if(contactList.containsKey(firstName)) {
			System.out.println("Contact Already Exists");
			return;
		} 
			
			System.out.println("Enter Last Name: ");
			String lastName = scannerObject.next();
			
			System.out.println("Enter Phone Number: ");
			long phoneNumber = scannerObject.nextLong();
			
			System.out.println("Enter Email: ");
			String email = scannerObject.next();
			
			System.out.println("Enter City: ");
			String city = scannerObject.next();
			
			System.out.println("Enter State: ");
			String state = scannerObject.next();
			
			System.out.println("Enter Zip Code: ");
			long zipCode = scannerObject.nextLong();
			
			person.setFirstName(firstName);
			person.setLastName(lastName);
			person.setPhoneNumber(phoneNumber);
			person.setEmail(email);
			person.setCity(city);
			person.setState(state);
			person.setZip(zipCode);
			addPersonToCity(person);
			addPersonToState(person);
			contactList.put(firstName, person);
			
			contactList.put(firstName, person);
		}

	

	public void addPersonToCity(ContactPerson contact) {
		if (personByCity.containsKey(contact.getCity())) {
			personByCity.get(contact.getCity()).add(contact);
		}
		else {
			ArrayList<ContactPerson> cityList = new ArrayList<ContactPerson>();
			cityList.add(contact);
			personByCity.put(contact.getCity(), cityList);
		}
	}

	public void addPersonToState(ContactPerson contact) {
		if (personByState.containsKey(contact.getState())) {			
			personByState.get(contact.getState()).add(contact);
		}
		else {
			ArrayList<ContactPerson> stateList = new ArrayList<ContactPerson>();
			stateList.add(contact);
			personByState.put(contact.getState(), stateList);
		}
	}

	public void editPerson() {
		
		ContactPerson person = new ContactPerson();

		System.out.println("Enter the first name:");
		String firstName = scannerObject.next();
		
		if(contactList.containsKey(firstName)) {
			person = contactList.get(firstName);
			
			System.out.println("\nChoose the attribute you want to change:");
			System.out.println("1.Last Name\n2.Phone Number\n3.Email\n4.City\n5.State\n6.ZipCode");
			int choice = scannerObject.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter the correct Last Name :");
				String lastName = scannerObject.next();
				person.setLastName(lastName);
				break;
			case 2:
				System.out.println("Enter the correct Phone Number :");
				long phoneNumber = scannerObject.nextLong();
				person.setPhoneNumber(phoneNumber);
				break;
			case 3:
				System.out.println("Enter the correct Email Address :");
				String email = scannerObject.next();
				person.setEmail(email);
				break;
			case 4:
				System.out.println("Enter the correct City :");
				String city = scannerObject.next();
				person.setCity(city);
				break;
			case 5:
				System.out.println("Enter the correct State :");
				String state = scannerObject.next();
				person.setState(state);
				break;
			case 6:
				System.out.println("Enter the correct ZipCode :");
				long zip = scannerObject.nextLong();
				person.setZip(zip);
				break;
			}
		}
		else {
			System.out.println("Book Does Not Exist");
		}
		
		
	}

	@Override
	public void deletePerson() {

		System.out.println("Enter the first name of the person to be deleted");
		String firstName = scannerObject.next();
		if(contactList.containsKey(firstName)) {
			contactList.remove(firstName);
			System.out.println("Successfully Deleted");
		}
		else {
			System.out.println("Contact Not Found!");
		}
		
	}

	@Override
	public void displayContents() {
		
		System.out.println("----- Contents of the Address Book "+this.getAddressBookName()+" -----");
		for (String eachContact : contactList.keySet()) {
			ContactPerson person = contactList.get(eachContact);
			System.out.println(person);
		}
		System.out.println("-----------------------------------------");

	}

}
