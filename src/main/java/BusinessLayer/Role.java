package BusinessLayer;

public enum Role {
    Admin("Admin"),
    Employee("Employee"),
    Client("Client");

    private String roleType;

    Role(String roleType){
        this.roleType = roleType;
    }

    public String getRoleType() {
        return roleType;
    }

    public static Role setRole(String roleType){
        switch (roleType){
            case "Admin":
                return Role.Admin;
            case "Employee":
                return  Role.Employee;
            case "Client":
                return Role.Client;
            default:
                return null;
        }
    }
}