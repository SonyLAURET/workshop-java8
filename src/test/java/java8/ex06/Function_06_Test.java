package java8.ex06;



import java8.data.Person;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import java.util.Objects;
import java.util.function.Supplier;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 06 - java.util.function.Supplier
 */
public class Function_06_Test {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    // tag::formatAge[]
    // compléter la méthode
    // la méthode retourne une chaîne de caractères de la forme [age=<AGE>] (exemple : [age=12])
    String formatAge(Supplier<Person> supplier) {
        return "[age="+supplier.get().getAge().toString()+"]";
    }
    // end::formatAge[]


    @Test
    public void test_supplier_formatAge() throws Exception {
        // compléter le test unitaire pour qu'il soit passant
    	Person p = new Person();
    	p.setAge(35);
        String result = formatAge(()->p);

        assertThat(result, is("[age=35]"));
    }

    @Test
    public void test_supplier_requireNonNull() throws Exception {

        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("require non null object");
        
        //compléter le test unitaire pour qu'il soit passant
        // le pointeur est placé, il reste juste à lui indiquer le message pour le repérer.
        Supplier<String> supplier = () ->"require non null object";
        
        // Avec un paramètre null, cette méthode déclenche un NullPointerException
        Objects.requireNonNull(null, supplier);

    }

}
