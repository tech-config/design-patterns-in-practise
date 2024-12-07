package com.design.patterns.facade.hometheatre;

import com.design.patterns.facade.hometheatre.items.AudioSystem;
import com.design.patterns.facade.hometheatre.items.DVDPlayer;
import com.design.patterns.facade.hometheatre.items.PlayStation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class HomeTheatreFacadeTest {

    private HomeTheatre audioSystemMock;
    private HomeTheatre dvdPlayerMock;
    private HomeTheatre playStationMock;
    private HomeTheatreFacade homeTheatreFacade;

    @BeforeEach
    void setUp() {
        audioSystemMock = Mockito.mock(AudioSystem.class);
        dvdPlayerMock = Mockito.mock(DVDPlayer.class);
        playStationMock = Mockito.mock(PlayStation.class);
        homeTheatreFacade = new HomeTheatreFacade();
    }

    @Test
    void testStartHomeTheatre() throws NoSuchFieldException, IllegalAccessException {
        //creating mock home theatre items list
        List<HomeTheatre> homeTheatreItemsMock = Arrays.asList(audioSystemMock, dvdPlayerMock, playStationMock);

        // Using reflection to modify state
        Field field = HomeTheatreFacade.class.getDeclaredField("homeTheatreItems");
        field.setAccessible(true);
        field.set(homeTheatreFacade, homeTheatreItemsMock);

        // Act
        homeTheatreFacade.startHomeTheatre();

        // Assert
        verify(audioSystemMock, times(1)).itemOperation(HomeTheatre.ItemOperation.values());
        verify(dvdPlayerMock, times(1)).itemOperation(HomeTheatre.ItemOperation.values());
        verify(playStationMock, times(1)).itemOperation(HomeTheatre.ItemOperation.values());


    }
}
