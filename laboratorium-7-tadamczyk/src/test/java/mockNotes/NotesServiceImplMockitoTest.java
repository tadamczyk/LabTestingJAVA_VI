package mockNotes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class NotesServiceImplMockitoTest {
  @Mock
  NotesStorage storage;

  @InjectMocks
  NotesServiceImpl service;
  String name;
  Note note1, note2;
  List<Note> notes;

  @Before
  public void setUp() {
    service = NotesServiceImpl.createWith(storage);
    name = "Tomasz Adamczyk";
    note1 = Note.of(name, 4.0f);
    note2 = Note.of(name, 5.0f);
    notes = Arrays.asList(new Note[] { note1, note2 });
  }

  @Test
  public void getAllNotesOfUnnamedTest() {
    when(storage.getAllNotesOf("unnamed")).thenReturn(null);
    assertThat(storage.getAllNotesOf("unnamed")).isNullOrEmpty();
  }

  @Test
  public void getAverageOfNotesTAdamczykTest() {
    when(storage.getAllNotesOf(name)).thenReturn(notes);
    assertThat(service.averageOf(name)).isEqualTo(4.5f).isBetween(4.0f, 5.0f);
  }

  @Test
  public void getAverageOfNotesWhenNotesIsEmptyTAdamczykTest() {
    when(storage.getAllNotesOf(name)).thenReturn(Arrays.asList(new Note[] {}));
    assertThat(service.averageOf(name)).isNaN();
  }

  @Test
  public void checkAverageOfNotesAfterAddNote1ForTAdamczykTest() {
    doAnswer((Answer<?>) invocation -> {
      Object arg0 = invocation.getArgument(0);
      assertEquals(note1, arg0);
      return null;
    }).when(storage).add(note1);
    doReturn(Arrays.asList(new Note[] { note1 })).when(storage).getAllNotesOf(name);
    service.add(note1);
    assertThat(service.averageOf(name)).isEqualTo(4.0f);
  }

  @Test
  public void checkAverageOfNotesAfterClearNotesTAdamczykTest() {
    doAnswer((Answer<?>) invocation -> {
      return null;
    }).when(storage).clear();
    doReturn(Arrays.asList(new Note[] {})).when(storage).getAllNotesOf(name);
    service.clear();
    assertThat(service.averageOf(name)).isNaN();
  }

  @After
  public void tearDown() {
    service = null;
    name = null;
    note1 = null;
    note2 = null;
    notes = null;
  }
}