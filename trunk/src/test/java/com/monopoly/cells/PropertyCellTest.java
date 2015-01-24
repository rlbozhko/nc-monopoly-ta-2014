package com.monopoly.cells;

/**
 * Created by Dmitriy on 22.01.2015.
 */
import com.monopoly.board.building.Building;
import com.monopoly.board.building.BuildingType;
import com.monopoly.board.cells.Monopoly;
import com.monopoly.board.cells.PropertyCell;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import com.monopoly.board.cells.PropertyStatus;
import org.junit.Test;
import org.junit.Assert;


public class PropertyCellTest {
    Monopoly abc = new Monopoly("MonopolyType");
    Building building = new Building(BuildingType.getTypeByText("CASTLE"), 100);
    PropertyCell prop = new PropertyCell("Name","Some property Cell",5,building,500,10,abc);

    @Test
    public void GetPriceTest()
    {
        Assert.assertEquals("Name", prop.getName());
    }

    @Test
    public void BuildBuildingTest()
    {
        //prop.buildBuilding( building ,1);
        //Assert.assertEquals("Some property Cell", prop.getBuildings());
    }

    @Test
    public void GetRentTest()
    {
        Assert.assertEquals(12, prop.getRent());
    }

    @Test
    public void GetStatusTest()
    {
        Assert.assertEquals(PropertyStatus.UNPLEDGED, prop.getStatus());
    }

    @Test
    public void GetMonopolyTest()
    {
        Assert.assertEquals( abc, prop.getMonopoly());
    }

    @Test
    public void GetMaxLevelTest()
    {
        Assert.assertEquals( 5, prop.getMaxLevel());
    }

  }
