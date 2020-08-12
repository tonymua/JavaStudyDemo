package demo.Entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.data.aerospike.mapping.Field;

import com.aerospike.client.admin.User;
import com.sun.xml.internal.ws.wsdl.writer.document.http.Address;

import sun.security.krb5.Credentials;

/**
 * @author:
 * @date: created in 13:01 2020/8/9
 * @version:
 */
public class Person {

    public enum Sex {
        MALE, FEMALE;
    }

    private String firstname;
    private HashMap myHashMap;
    private String lastname;
    private String email;
    private Integer age;
    @SuppressWarnings("unused")
    private Sex sex;
    Date createdAt;
    List<String> skills;
    private Address address;
    @Field(value = "ShipAddresses")
    private Set<Address> shippingAddresses;
    User creator;
    Credentials credentials;
}