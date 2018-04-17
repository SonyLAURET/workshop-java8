package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

    // tag::PersonToAccountMapper[]
	//generic
    interface PersonToAnyMapper<T> {
        T map(Person p);
    }
    // end::PersonToAccountMapper[]

    // tag::map[]
    //care here, type is before the method because the class don't have a type.
    private <T> List<T> map(List<Person> personList, PersonToAnyMapper<T> mapper) {
        // implémenter la méthode
    	List<T> persons = new ArrayList<>();
    	for (Person person : personList) {   	
			if (mapper.map(person) != null) {				
				persons.add(mapper.map(person))	;			
			}
		}
        return persons;
    }
    // end::map[]


    // tag::test_map_person_to_account[]
    @Test
    public void test_map_person_to_account() throws Exception {

        List<Person> personList = Data.buildPersonList(100);
        
        // TODO transformer la liste de personnes en liste de comptes
        // TODO tous les objets comptes ont un solde à 100 par défaut
        List<Account> result = map(personList,p -> {
        	Account a = new Account();
        	for (Person person : personList) {
        	a.setOwner(person);
        	a.setBalance(100);}
			return a;
        });

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(hasProperty("balance", is(100))));
        assertThat(result, everyItem(hasProperty("owner", notNullValue())));
    }
    // end::test_map_person_to_account[]

    // tag::test_map_person_to_firstname[]
    @Test
    public void test_map_person_to_firstname() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        //transformer la liste de personnes en liste de prénoms
        
        //with generic method
        List<String> result = map(personList, p -> p.getFirstname());
        
        //without  generic method
        /* List<String> result = new ArrayList<>();
       Iterator<Person> p = personList.iterator();
        while (p.hasNext()) {
			result.add(((Person)p.next()).getFirstname());
        }*/
      

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(instanceOf(String.class)));
        assertThat(result, everyItem(startsWith("first")));
    }
    // end::test_map_person_to_firstname[]
}
