/**
 * @author Vandeputte François
 *
 */
public class LoginInfo {

    private String login;

    public LoginInfo(String login)throws IllegalArgumentException{
        if(login == null || login.length() != 7 ||!login.substring(0,4).equals("info"))
            throw new IllegalArgumentException("login incorrect");
        this.login = login;
    }

    public String toString(){
        return "/n" + login + "   " + login;
    }

    public int hashCode(){
        return Integer.parseInt(login.substring(4,7)) - 1;
    }
}


