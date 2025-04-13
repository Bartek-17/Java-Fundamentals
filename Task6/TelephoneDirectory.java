import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;

public class TelephoneDirectory {
    public static void main(String[] args) {
        TreeMap<TelephoneNumber, TelephoneEntry> directory = new TreeMap<>();

        TelephoneNumber tnPolandPerson = new TelephoneNumber("48", "123456789");
        Address addressPolandPerson = new Address("Poland", "Warsaw", "Ulica 12");
        Person personPoland = new Person("Jan", "Kowalski", addressPolandPerson, tnPolandPerson);

        TelephoneNumber tnPolandCompany = new TelephoneNumber("48", "224567890");
        Address addressPolandCompany = new Address("Poland", "Krakow", "Włoska 10");
        Company companyPoland = new Company("PolskaTech", addressPolandCompany, tnPolandCompany);

        TelephoneNumber tnGermanyPerson = new TelephoneNumber("49", "1512345678");
        Address addressGermanyPerson = new Address("Germany", "Berlin", "Alexanderplatz 3");
        Person personGermany = new Person("Max", "Mustermann", addressGermanyPerson, tnGermanyPerson);

        TelephoneNumber tnGermanyCompany = new TelephoneNumber("49", "3034567890");
        Address addressGermanyCompany = new Address("Germany", "Munich", "Bayerstrasse 10");
        Company companyGermany = new Company("AutoBau", addressGermanyCompany, tnGermanyCompany);

        TelephoneNumber tnEnglandPerson = new TelephoneNumber("44", "7712345678");
        Address addressEnglandPerson = new Address("England", "London", "Oxford St 20");
        Person personEngland = new Person("Emily", "Smith", addressEnglandPerson, tnEnglandPerson);

        TelephoneNumber tnEnglandCompany = new TelephoneNumber("44", "2087654321");
        Address addressEnglandCompany = new Address("England", "Manchester", "King St 15");
        Company companyEngland = new Company("BritTech Ltd.", addressEnglandCompany, tnEnglandCompany);

        directory.put(tnPolandPerson, personPoland);
        directory.put(tnPolandCompany, companyPoland);
        directory.put(tnGermanyPerson, personGermany);
        directory.put(tnGermanyCompany, companyGermany);
        directory.put(tnEnglandPerson, personEngland);
        directory.put(tnEnglandCompany, companyEngland);

        Iterator<Map.Entry<TelephoneNumber, TelephoneEntry>> iterator = directory.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<TelephoneNumber, TelephoneEntry> entry = iterator.next();
            System.out.println(entry.getValue().description());
        }
    }
}

class TelephoneNumber implements Comparable<TelephoneNumber> {
    private String countryCode;
    private String localNumber;

    public TelephoneNumber(String countryCode, String localNumber) {
        this.countryCode = countryCode;
        this.localNumber = localNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getLocalNumber() {
        return localNumber;
    }

    @Override
    public int compareTo(TelephoneNumber other) {
        int countryCodeComparison = this.countryCode.compareTo(other.countryCode);
        if (countryCodeComparison != 0) {
            return countryCodeComparison;
        }
        return this.localNumber.compareTo(other.localNumber);
    }

    @Override
    public String toString() {
        return "+" + countryCode + "-" + localNumber;
    }
}

class Address {
    private String country;
    private String city;
    private String street;

    public Address(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
    }

    @Override
    public String toString() {
        return country + ", " + city + ", " + street;
    }
}

abstract class TelephoneEntry {
    protected TelephoneNumber telephoneNumber;

    public TelephoneEntry(TelephoneNumber telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public abstract String description();
}

class Person extends TelephoneEntry {
    private String name;
    private String lastName;
    private Address address;

    public Person(String name, String lastName, Address address, TelephoneNumber telephoneNumber) {
        super(telephoneNumber);
        this.name = name;
        this.lastName = lastName;
        this.address = address;
    }

    @Override
    public String description() {
        return "Person: " + name + " " + lastName + ", Address: " + address + ", Phone: " + telephoneNumber;
    }
}

class Company extends TelephoneEntry {
    private String companyName;
    private Address address;

    public Company(String companyName, Address address, TelephoneNumber telephoneNumber) {
        super(telephoneNumber);
        this.companyName = companyName;
        this.address = address;
    }

    @Override
    public String description() {
        return "Company: " + companyName + ", Address: " + address + ", Phone: " + telephoneNumber;
    }
}
