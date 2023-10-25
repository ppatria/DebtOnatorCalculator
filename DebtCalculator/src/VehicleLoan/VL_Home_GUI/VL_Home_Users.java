package VehicleLoan.VL_Home_GUI;

public class VL_Home_Users {
    private String newUser;
    private String existingUser;

    public VL_Home_Users(String newUser, String existingUser) {
        this.newUser = newUser;
        this.existingUser = existingUser;
    }

    public String getNewUser() {
        return newUser;
    }

    public String getExistingUser() {
        return existingUser;
    }
}
