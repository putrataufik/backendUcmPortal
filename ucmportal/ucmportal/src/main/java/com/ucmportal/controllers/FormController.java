package com.ucmportal.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ucmportal.dto.FormDto;
import com.ucmportal.dto.FormListAccDto;
import com.ucmportal.dto.MyActivityData;
import com.ucmportal.dto.UsersFormDetails;
import com.ucmportal.entities.Form;
import com.ucmportal.entities.User;
import com.ucmportal.services.FormService;
import com.ucmportal.services.UserService;

@RestController
@RequestMapping("/ucmportal")
public class FormController {

    @Autowired
    private FormService formService;
    @Autowired
    private UserService userService;

    // ================================ save form by penggunanya
    // =============================
    @PostMapping("/{name}/saveform")
    public String saveForm(@PathVariable("name") String name,
            @RequestParam("logo_form") MultipartFile logoFile,
            @RequestParam("title_form") String title,
            @RequestParam("type_form") String type,
            @RequestParam("date_start_form") Date date_start,
            @RequestParam("date_end_form") Date date_end,
            @RequestParam("description_form") String description,
            @RequestParam("link_google_form") String link_google_form) throws IOException {

        User user = userService.getUserByUsername(name);
        if (user == null) {
            throw new RuntimeException("User dengan username '" + name + "' tidak ditemukan.");
        }

        byte[] logo = null;
        if (logoFile != null && !logoFile.isEmpty()) {
            logo = logoFile.getBytes();
        }
        String status = "Process";
        Form form = new Form();
        form.setTitle(title);
        form.setType(type);
        form.setDateStart(date_start);
        form.setDateEnd(date_end);
        form.setDescription(description);
        form.setLogo(logo);
        form.setLink_google_form(link_google_form);
        form.setStatus(status);
        form.setUser(user);

        formService.saveForm(title, type, date_start, date_end, description, logoFile, link_google_form, status, user);

        return "Formulir berhasil disimpan.";
    }

    // =======================ini data yang akan ditampilkan di
    // home=========================================================
    @GetMapping("/accepted")
    public ResponseEntity<List<FormListAccDto>> findAllAccepted() {
        List<FormListAccDto> formDtos = formService.findAllAccepted();
        return new ResponseEntity<>(formDtos, HttpStatus.OK);
    }

    // ========================Mendapatkan data formulir berdasarkan
    // judul=================================================
    @GetMapping("/form/{title}")
    public ResponseEntity<List<FormDto>> getFormByTitleUser(@PathVariable("title") String title) {
        List<FormDto> forms = formService.findByTitle(title);
        if (forms == null || forms.isEmpty()) {
            throw new RuntimeException("No forms with title '" + title + "' were found.");
        }
        return new ResponseEntity<>(forms, HttpStatus.OK);
    }

    // ===================menampilkan semua request (untuk admin)==========================================
    @GetMapping("/listrqform")
    public ResponseEntity<List<UsersFormDetails>> findAllFormWithUserByStatus() {
        List<UsersFormDetails> formList = formService.findAllFormWithUserByStatus("Process");
        return new ResponseEntity<>(formList, HttpStatus.OK);
    }

    // =============================Mengupdate status formulir (untuk admin)=======================================
    @PutMapping("/updateform/{title}/{status}")
    public ResponseEntity<?> updateStatusForm(@PathVariable("title") String title,
            @PathVariable("status") String status) {
        try {
            formService.updateFormStatus(title, status);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Gagal memperbarui status formulir: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Status formulir berhasil diperbarui menjadi " + status + ".");
    }

    // ========================== endpoint untuk search =============================================
    @GetMapping("/searchform/{title}")
    public ResponseEntity<List<String>> searchForm(@PathVariable("title") String title) {
        List<String> titles = formService.searchForms(title);
        return ResponseEntity.ok(titles);
    }

    // ===========================endpoint untuk myActivity===========================================
    @GetMapping("/{name}/myforms")
    public ResponseEntity<List<MyActivityData>> getUserForms(@PathVariable String name) {
        List<MyActivityData> forms = formService.getFormsByUser(name);

        if (forms == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(forms);
    }
}


