/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author huytu
 */
public class EmployeeTerritory {
    private int employeeID;
    private String territoryID;

    public EmployeeTerritory() {
    }

    public EmployeeTerritory(int employeeID, String territoryID) {
        this.employeeID = employeeID;
        this.territoryID = territoryID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getTerritoryID() {
        return territoryID;
    }

    public void setTerritoryID(String territoryID) {
        this.territoryID = territoryID;
    }

    @Override
    public String toString() {
        return "EmployeeTerritory{" + "employeeID=" + employeeID + ", territoryID=" + territoryID + '}';
    }
    
    
}
