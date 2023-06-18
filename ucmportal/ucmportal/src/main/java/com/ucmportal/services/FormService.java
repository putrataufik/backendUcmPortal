package com.ucmportal.services;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ucmportal.dto.FormDto;
import com.ucmportal.dto.FormListAccDto;
import com.ucmportal.dto.MyActivityData;
import com.ucmportal.dto.UsersFormDetails;
import com.ucmportal.entities.Form;
import com.ucmportal.entities.User;
import com.ucmportal.repositories.FormRepo;
import com.ucmportal.repositories.UserRepo;


@Service
public class FormService {

    @Autowired
    private FormRepo formRepo;

    @Autowired
    private UserRepo userRepo;

    public FormService(FormRepo formRepo, UserRepo userRepo) {
        this.formRepo = formRepo;
        this.userRepo = userRepo;
    }

    public String saveForm(String title, String type, Date date_start, Date date_end, String description,
            MultipartFile logoFile, String link_google_form, String Status, User user) throws IOException {
        Form form = new Form();
        form.setTitle(title);
        form.setType(type);
        form.setDateStart(date_start);
        form.setDateEnd(date_end);
        form.setDescription(description);
        form.setLogo(logoFile.getBytes()); // Konversi MultipartFile menjadi byte[]
        form.setLink_google_form(link_google_form);
        form.setStatus(Status);
        form.setUser(user);
        formRepo.save(form);
        return "Form Saved In DB";
    }

    // Di dalam FormService Anda
    public void updateFormStatus(String title, String status) {
        List<Form> forms = formRepo.findByTitle(title);
        if (forms != null && !forms.isEmpty()) {
            Form form = forms.get(0); // Mengambil form pertama jika ada banyak form dengan judul yang sama
            form.setStatus(status);
            formRepo.save(form);
        } else {
            throw new RuntimeException("Formulir dengan judul '" + title + "' tidak ditemukan.");
        }
    }

    public Iterable<Form> findAll() {
        return formRepo.findAll();
    }

    public List<FormDto> findByTitle(String title) {
        List<Form> forms = formRepo.findByTitle(title);
        return forms.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<String> searchForms(String title) {
        LocalDate today = LocalDate.now();
    List<Form> forms = formRepo.findByTitleContainsIgnoreCaseAndStatus(title, "accepted");
    List<String> titles = forms.stream()
    .filter(form -> convertToLocalDate(form.getDateEnd()).isAfter(today)
                        || convertToLocalDate(form.getDateEnd()).isEqual(today))
    .map(Form::getTitle).collect(Collectors.toList());
    return titles;
}


    public List<FormListAccDto> findAllAccepted() {
        LocalDate today = LocalDate.now();
        List<Form> forms = formRepo.findByStatusOrderByDateStartAsc("Accepted");

        return forms.stream()
                .filter(form -> convertToLocalDate(form.getDateEnd()).isAfter(today)
                        || convertToLocalDate(form.getDateEnd()).isEqual(today))
                .map(FormListAccDto::fromForm)
                .collect(Collectors.toList());
    }

    private LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toLocalDate();
    }

    private FormDto convertToDTO(Form form) {
        FormDto formDTO = new FormDto();
        formDTO.setId(form.getId());
        formDTO.setTitle(form.getTitle());
        formDTO.setType(form.getType());
        formDTO.setDateStart(form.getDateStart());
        formDTO.setDateEnd(form.getDateEnd());
        formDTO.setDescription(form.getDescription());
        formDTO.setLinkGoogleForm(form.getLink_google_form());
        formDTO.setStatus(form.getStatus());
        formDTO.setLogo(form.getLogo());
        // Set other properties as needed
        return formDTO;
    }

    public List<UsersFormDetails> findAllFormWithUserByStatus(String status) {
        List <Form> requestList = formRepo.findByStatus(status);
        List <UsersFormDetails> usersFormDetails = new ArrayList<>();

        requestList.forEach(list -> usersFormDetails.add(new UsersFormDetails(list.getUser().getName()
        ,list.getUser().getNim(),list.getUser().getMajor(),list.getTitle(),list.getStatus())));
        return usersFormDetails;
    }


    public Iterable<Form> findByUsername(User username) {
        return formRepo.findByUser(username);
    }

    public List<MyActivityData> getFormsByUser(String username) {
        User user = userRepo.findByName(username);

        if (user != null) {
            return formRepo.findByUser(user)
                    .stream()
                    .map(form -> new MyActivityData(
                            form.getId(),
                            form.getTitle(),
                            form.getType(),
                            form.getDateStart(),
                            form.getDateEnd(),
                            form.getStatus()))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
