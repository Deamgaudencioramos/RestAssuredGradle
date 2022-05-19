package restassured.gradle.teste;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;
import restassured.gradle.dominio.Usuario;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class TesteResgistro extends TesteBase {
    private static final String  REGISTRA_USUARIO_ENDPOIN = "/register";
    private static final String LOGIN_ENDPOINT = "/login";

    @BeforeClass
    public static void  setupRegistro(){
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .build();
    }


    @Test
    public  void NaoEfetuaRegistroQuandoSenhaEstaFaltando(){
        Usuario usuario = new Usuario();
        usuario.setEmail("deam@teste.com.br");

        given().
                body(usuario).
        when().
                post(REGISTRA_USUARIO_ENDPOIN).
        then().
                body("error",is ("Missing password"));
    }

    //esse teste deveria estar no loginTeste
    @Test
    public  void testeLoginNaoEfetuado(){
        Usuario usuario = new Usuario();
        usuario.setEmail("deam@teste.com.br");

        given().
                body(usuario).
        when().
                post(LOGIN_ENDPOINT).
        then().
                body("error",is ("Missing password"));
    }
}
