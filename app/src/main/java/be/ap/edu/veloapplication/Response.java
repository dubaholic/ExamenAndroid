package be.ap.edu.veloapplication;

import java.util.ArrayList;

public class Response
{
    private ArrayList <VeloStation > veloStations = null;
    public ArrayList< VeloStation > getVeloStations()
    {
        return veloStations;
    }
    public void setVeloStations(ArrayList < VeloStation > veloStations)
    {
        this.veloStations = veloStations;
    }
}