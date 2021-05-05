package com.tacx.activity.application.command.deleteactivity;

import com.tacx.activity.domain.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class DeleteActivityHandlerTest {

    private DeleteActivityHandler deleteActivityHandler;

    @Mock
    private ActivityService activityService;

    @BeforeEach
    public void setUp() {
        deleteActivityHandler = new DeleteActivityHandler(activityService);
    }


    @Test
    void should_handle_deleteActivityCommand() {
        //given
        DeleteActivityCommand deleteActivityCommand = new DeleteActivityCommand("1");
        doNothing().when(activityService).delete(deleteActivityCommand.getActivityId());

        //when
        deleteActivityHandler.handle(deleteActivityCommand);

        //then
        verify(activityService).delete(deleteActivityCommand.getActivityId());
    }
}
