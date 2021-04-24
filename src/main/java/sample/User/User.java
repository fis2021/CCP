package sample.User;
import sample.register.register;
public class User {

    private String username;
    private String password;
    private String gmail;
    private String role;
    private boolean check;
    private int id;
    public User(){

    }
    public User(String username, String password, String gmail, String roole,boolean check){
        this.username=username;
        this.password=password;
        this.gmail=gmail;
        this.role=roole;
        this.check=check;
        this.id=register.getId();
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
    public String getGmail(){
        return gmail;
    }

    public String getRole(){
        return role;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setGmail(String gmail){
        this.gmail=gmail;
    }

    public void setRole(String role){
        this.role = role;
    }

    public int getId(int id)
    {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return role != null ? role.equals(user.role) : user.role == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return "User: " + username +"- Password: " + password +
                "- e-mail: " + gmail +"- Role: " + role;
    }

}

