package com.jobforge.backend;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;




@RestController
//Frontend den Zugriff erlauben, da es auf einem anderen Port läuft
@CrossOrigin(origins = "http://localhost:8091")
public class TestController {
    
    //Test endpoint to check if the backend is working
    @GetMapping("/")
    public String helloWorld() {       
        return "Hello World!";
    }
    
    @GetMapping("/api/test")
    public Map<String, String> getMethodName() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Backend wurde erfolgreich angesprochen und das ist die Antwort");
        return response;
    }

    //bekommt die Files vom backend zur Validierung
    @PostMapping("/api/upload")
    public String validateFiles(@RequestParam("data") MultipartFile[] entity) {
        //Validation Checks
        boolean isLengthValid = validateFileCount(entity);
        boolean isFileSizeValid = validateFileSize(entity);
        boolean isfileTypeAllowed = validateFileType(entity);
        boolean isFilePathValid = validateFilePath(entity);
        boolean isFileEmpty = validateFileEmpty(entity);

        //Response decision
        if(!isFileEmpty) return "error: Datei scheint leer zu sein";
        if(!isLengthValid) return "error: Zu Viele Dateien";
        if(!isFileSizeValid) return "error: mindestens eine Datei ist zu Groß";
        if(!isfileTypeAllowed) return "error: Das Format mindestens einer Datei ist nicht erlaubt";
        if(!isFilePathValid) return "error: Dateipfad ist ungültig";

        return "success";
    }
    //Validierung der Anzahl der Files (max: 10)
    private boolean validateFileCount(MultipartFile[] e){
        if(e.length >= 10) {
            return false;
        }else{
            return true;
        }
    }

    //Validierung der Größe der Files (max: 5MB)
    private boolean validateFileSize(MultipartFile[] e){
        final int MAX_FILE_SIZE = 5 *1024 * 1024;
        int counter = 0;

        for (MultipartFile file : e) {            
            if(file.getSize() > MAX_FILE_SIZE){
                counter++;
            }
        }

        if(counter != 0){
            return false;
        }else{
            return true;
        }
    }

    //Validierung der Dateitypen (nur echte PDF, DOC und DOCX erlaubt)
    private boolean validateFileType(MultipartFile[] e){
        
        for(MultipartFile file : e){

            /**Dateiendung Validierung */
            String fileName = file.getOriginalFilename();

            if (fileName == null || fileName.isEmpty()) {
                return false;
            }

            String lowerName = fileName.toLowerCase();

            boolean claimsToBePdf = lowerName.endsWith(".pdf");
            boolean claimsToBeDoc = lowerName.endsWith(".doc");
            boolean claimsToBeDocx = lowerName.endsWith(".docx");

            if (!claimsToBePdf && !claimsToBeDoc && !claimsToBeDocx) {
                return false;
            }


            try(InputStream is = file.getInputStream()){

                /**MagicByte und ContentType Validierung */

                byte[] header = new byte[4];
                int bytesRead = is.read(header);
                
                //Validiert ob Datei leer ist
                if(bytesRead < 4) return false;

                //Hex-String der Datei holen
                StringBuilder hexBuilder = new StringBuilder();
                for(byte b : header){
                    hexBuilder.append(String.format("%02X", b));
                }
                String fileSignature = hexBuilder.toString();

                boolean isValidPdf = fileSignature.equals("25504446"); //PDF-Dateien
                boolean isValidZIP = fileSignature.equals("504B0304"); //ZIP-Formate wie docx xlsx usw
                boolean isValidDoc = fileSignature.equals("D0CF11E0"); //DOC-Dateien
                boolean isValidDocx = isValidZIP && "application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(file.getContentType());

                if(claimsToBePdf && !isValidPdf){
                    return false;
                }
                if(claimsToBeDoc && !isValidDoc){
                    return false;
                }
                if(claimsToBeDocx && !isValidDocx){
                    return false;
                }


            }catch(Exception ex){
                System.out.println(ex);
            }
        }
        return true;
    }

    //Validierung der korrupter Dateipfade
    private boolean validateFilePath(MultipartFile[] e){
        for(MultipartFile file : e){
            String saveFileName = StringUtils.cleanPath(file.getOriginalFilename());
            if(saveFileName.contains("..") || saveFileName.isEmpty()){
                return false;
            }
        }
        return true;
    }

    //Validierung ob Datei Leer ist
    private boolean validateFileEmpty(MultipartFile[] e){
        for(MultipartFile file : e){

            /**ob Datei Leer ist validieren */
            if (file.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
