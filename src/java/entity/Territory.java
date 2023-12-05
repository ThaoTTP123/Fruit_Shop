/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author huytu
 */
public class Territory {

    private String territoryID,territoryDescription;
    private int regionID;

    public Territory() {
    }

    public Territory(String territoryID, String territoryDescription, int regionID) {
        this.territoryID = territoryID;
        this.territoryDescription = territoryDescription;
        this.regionID = regionID;
    }

    public String getTerritoryID() {
        return territoryID;
    }

    public void setTerritoryID(String territoryID) {
        this.territoryID = territoryID;
    }

    public String getTerritoryDescription() {
        return territoryDescription;
    }

    public void setTerritoryDescription(String territoryDescription) {
        this.territoryDescription = territoryDescription;
    }

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }

    @Override
    public String toString() {
        return "Territory{" + "territoryID=" + territoryID + ", territoryDescription=" + territoryDescription + ", regionID=" + regionID + '}';
    }
    
    
}
