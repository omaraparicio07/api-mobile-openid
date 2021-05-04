package mx.edu.ebc.security
import groovy.transform.ToString

@ToString
class KeycloakUser {
  String email
  String username
  List<String> roles
}