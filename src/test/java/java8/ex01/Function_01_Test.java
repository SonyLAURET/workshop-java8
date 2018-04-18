package java8.ex01;

import java8.data.Account;
import java8.data.Person;
import org.junit.Test;

import java.util.function.Function;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


/**
 * Exercice 01 - java.util.function.Function
 */
public class Function_01_Test {

    /******** PART 1 - Integer -> Person *******/

    // tag::intToPerson[]
    //  Compléter la définition de cette fonction
    //  Cette fonction permet de transformer un entier en objet Person
    //  le prenom sera de la forme "first_<ENTIER>"
    //  le nom sera de la forme "last_<ENTIER>"
    //	l'age sera de la forme "<ENTIER>"
    // le mot de passe sera de la forme "pass_<ENTIER>"
    private Function<Integer, Person> intToPerson = i->{
    	Person p = new Person();
    	p.setFirstname("first_"+i);
    	p.setLastname("last_"+i);
    	p.setAge(i);
    	p.setPassword("pass_"+i);
    	return p;
    };
    // end::intToPerson[]

    @Test
    public void test_intToPerson() throws Exception {

        // invoquer la fonction intToPerson avec en paramètre l'entier 10.
        Person result = intToPerson.apply(10);

        assertThat(result, hasProperty("firstname", is("first_10")));
        assertThat(result, hasProperty("lastname", is("last_10")));
        assertThat(result, hasProperty("age", is(10)));
        assertThat(result, hasProperty("password", is("pass_10")));
    }

    /******** PART 2 - Person -> Account *******/

    // tag::personToAccount[]
    //  Compléter la définition de cette fonction
    //  la propriété owner est valorisé avec la personne en paramètre
    //  la propriété balance est valorisé à 1000
    private Function<Person, Account> personToAccount = p  ->{
    	Account a = new Account();
    	a.setOwner(p);
    	a.setBalance(1000);
    	return a;
    };
    // end::personToAccount[]

    @Test
    public void test_personToAccount() throws Exception {

        Person person = new Person("Jules", "France", 10, "pass");

        // invoquer la fonction personToAccount
        Account result = personToAccount.apply(person);

        assertThat(result, hasProperty("owner", is(person)));
        assertThat(result, hasProperty("balance", is(1000)));
    }


    /******** PART 3 - Integer -> Account avec compose *******/

    // tag::intToAccountWithCompose[]
    //  Compléter la définition de cette fonction
    //  Utiliser la méthode compose pour réutiliser les fonctions intToPerson et personToAccount
    private Function<Integer, Account> intToAccountWithCompose = i -> {
    	return personToAccount.compose(intToPerson).apply(i);
    };
    // end::intToAccountWithCompose[]


    @Test
    public void test_intToAccount_with_Compose() throws Exception {

        // invoquer la fonction intToAccountWithCompose avec l'entier 10
        Account result = intToAccountWithCompose.apply(10);

        assertThat(result.getOwner(), hasProperty("firstname", is("first_10")));
        assertThat(result, hasProperty("balance", is(1000)));
    }

    /******** PART 4 - Integer -> Account avec andThen *******/

    // tag::intToAccountWithAndThen[]
    //  Compléter la définition de cette fonction
    //  Utiliser la méthode andThen pour réutiliser les fonctions intToPerson et personToAccount
    private Function<Integer, Account> intToAccountWithAndThen = i -> {
    	return intToPerson.andThen(personToAccount).apply(i);
    };
    // end::intToAccountWithAndThen[]

    @Test
    public void test_intToAccount_with_AndThen() throws Exception {

        // invoquer la fonction intToAccountWithAndThen avec l'entier 11
        Account result = intToAccountWithAndThen.apply(11);

        assertThat(result.getOwner(), hasProperty("firstname", is("first_11")));
        assertThat(result, hasProperty("balance", is(1000)));
    }
}
