package com.example.nairy1.controller;
import com.example.nairy1.controller.User.Notes;
import com.example.nairy1.controller.service.Notes_services;
import com.example.nairy1.controller.service.Password_services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin
public class notesController {
    @Autowired
    public Notes_services notes_services;

    @PostMapping("/notes/addNotes")
    public boolean addNotes(@RequestBody Notes N){
        return this.notes_services.addNote(N.getUserid(),N.getTitle(),N.getTag(),N.getDescription());
    }

    @PostMapping("/notes/fetchNotes")
    public List<Notes> fetchNotes(@RequestBody Notes N){
        return this.notes_services.fetchNotes(N.getUserid(),N.getTitle(),N.getTag(),N.getDescription());
    }

    @PostMapping("/note/delete")
    public String deletenote(@RequestBody Notes N){
        return this.notes_services.deleteNote(N.getUserid(),N.getTitle(),N.getTag(),N.getDescription());
    }
}

