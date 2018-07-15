package mockNotes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.MockType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(EasyMockRunner.class)
public class NotesServiceImplEasyMockTest {
  @Mock(type = MockType.NICE)
  NotesStorage storage;

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
  public void getNotesOfTAdamczykTest() {
    expect(storage.getAllNotesOf(name)).andReturn(notes);
    replay(storage);
    assertThat(storage.getAllNotesOf(name)).hasSize(2).contains(note1);
  }

  @Test
  public void getAverageOfNotesTAdamczykTest() {
    expect(storage.getAllNotesOf(name)).andReturn(notes);
    replay(storage);
    service.add(note1);
    service.add(note2);
    assertThat(service.averageOf(name)).isEqualTo(4.5f);
  }

  @Test
  public void clearAllNotesTAdamczykTest() {
    expect(storage.getAllNotesOf(name)).andReturn(null);
    replay(storage);
    service.clear();
    assertThat(storage.getAllNotesOf(name)).isNullOrEmpty();
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