package com.addressBook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

	public class AddressBookDirectory {
		
		public AddressBook addressBook;
		Scanner scannerObject = new Scanner(System.in);
		Map<String,AddressBook> addressBookDirectory = new HashMap<String,AddressBook>();

		public void operationDirectory() {

			boolean moreChanges = true;
			do {

				System.out.println("\nChoose the operation on the Directory you want to perform");
				System.out.println(
						"1.Add an Address Book\n2.Edit Existing Address Book\n3.Search Person By City\n4.Search Person By State\n5.View By City\n6.View By State\n7.Display Address book Directory\n8.Exit Address book System");

				switch (scannerObject.nextInt()) {
				case 1:
					addAddressBook();
					break;
				case 2:
					editAddressBook();
					break;
				case 3:
					searchByCity();
					break;
				case 4:
					searchByState();
					break;
				case 5:
					displayPeopleByRegion(AddressBook.personByCity);
					break;
				case 6:
					displayPeopleByRegion(AddressBook.personByState);
					break;
				case 7:
					displayDirectoryContents();
					break;
				case 8:
					moreChanges = false;
					System.out.println("Exiting Address Book Directory !");
				}

			} while (moreChanges);
		}

		public void addAddressBook() {

			System.out.println("Enter the name of the Address Book you want to add");
			String bookNameToAdd = scannerObject.next();

			if(addressBookDirectory.containsKey(bookNameToAdd)) {
				System.out.println("Book Name Already Exists");
				return;
			}
			AddressBook addressBook = new AddressBook();
			addressBook.setAddressBookName(bookNameToAdd);
			addressBookDirectory.put(bookNameToAdd, addressBook);

		}

		public void editAddressBook() {

			System.out.println("Enter the Name of the Address Book which you want to edit:");
			String addressBookToEdit = scannerObject.next();

			if(addressBookDirectory.containsKey(addressBookToEdit)) {
				addressBook = addressBookDirectory.get(addressBookToEdit);
				addressBook.operation();
			}
			else {
				System.out.println("Book Does Not Exist");
			}

		}
		
		public void searchByCity() {
			
			System.out.println("Enter the name of the City where the Person resides : ");
			String cityName = scannerObject.next();
			System.out.println("Enter the name of the Person : ");
			String personName = scannerObject.next();
			
			for(AddressBook addressBook : addressBookDirectory.values()) {
				ArrayList<ContactPerson> contactList = addressBook.getContact();
				contactList.stream()
					.filter(person -> person.getFirstName().equals(personName) && person.getCity().equals(cityName))
					.forEach(person -> System.out.println(person));
			}		
		}
		
		public void searchByState() {
			
			System.out.println("Enter the name of the State where the Person resides : ");
			String stateName = scannerObject.next();
			System.out.println("Enter the name of the Person : ");
			String personName = scannerObject.next();
			
			for(AddressBook addressBook : addressBookDirectory.values()) {
				ArrayList<ContactPerson> contactList = ((AddressBook) addressBook).getContact();
				contactList.stream()
					.filter(person -> person.getFirstName().equals(personName) && person.getState().equals(stateName))
					.forEach(person -> System.out.println(person));		
			} 

		}
		
		public void displayPeopleByRegion(HashMap<String, ArrayList<ContactPerson>> listToDisplay) {
			ArrayList<ContactPerson> list;
			for (String name : listToDisplay.keySet()) {
				System.out.println("People residing in: " + name);
				list = listToDisplay.get(name);
				for (ContactPerson contact : list) {
					System.out.println(contact);
				}
			}

		}

		public void displayDirectoryContents() {

			System.out.println("----- Contents of the Address Book Directory-----");
			for (String eachBookName : addressBookDirectory.keySet()) {
				
					System.out.println(eachBookName);
			}
			System.out.println("-----------------------------------------");
		}
}

