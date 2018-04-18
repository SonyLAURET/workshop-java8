package java8.ex03;

import java8.data.Person;
import org.junit.Test;

import java.util.function.BinaryOperator;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 03 - java.util.function.BinaryOperator
 */
public class Function_03_Test {

    //  tag::makeAChild[]
    // Compléter la fonction makeAChild
    //  l'enfant possède le nom du père
    //  l'enfant possède le prenom "<PRENOM_PERE> <PRENOM_MERE>"
    //  l'age de l'enfant est 0
    //  le mot de passe de l'enfant est null
    BinaryOperator<Person> makeAChild = (father,mother)->{
    	Person child=new Person();
    	child.setLastname(father.getLastname());
    	child.setFirstname(father.getFirstname()+" "+mother.getFirstname());
    	child.setAge(0);
    	child.setPassword(null);
    	return child;
    };
    //  end::makeAChild[]


    @Test
    public void test_makeAChild() throws Exception {

        Person father = new Person("John", "France", 25, "johndoe");
        Person mother = new Person("Aline", "Lebreton", 22, "alino");

        // compléter le test pour qu'il soit passant
        Person child = makeAChild.apply(father,mother);

        assertThat(child, hasProperty("firstname", is("John Aline")));
        assertThat(child, hasProperty("lastname", is("France")));
        assertThat(child, hasProperty("age", is(0)));
        assertThat(child, hasProperty("password", nullValue()));
    }

}
