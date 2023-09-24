package pe.edu.cibertec.appwebformularios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.cibertec.appwebformularios.model.ImcModel;

@Controller
public class FormImcController {
    @GetMapping("/calcularimc")
    public String index(Model model) {
        model.addAttribute("verresultado", false);
        model.addAttribute("imcmodel", new ImcModel());
        return "formImc";
    }
    @PostMapping("/calcularimc")
    public String calcularImc(@ModelAttribute("imcmodel") ImcModel imcmodel, Model model) {
        Double tallam = imcmodel.getAltura() / 100;
        Double valorimc = imcmodel.getPeso() / (tallam * tallam);
        String resultadoimc = "";
        String coloralert = "alert-danger";
        if (valorimc <= 18.5) {
            resultadoimc = "Por debajo del peso.";
            coloralert = "alert-dark";
        } else if (valorimc <= 25) {
            resultadoimc = "Con peso normal";
            coloralert = "alert-primary";
        } else if (valorimc <= 30) {
            resultadoimc = "Con sobrepeso";
            coloralert = "alert-warning";
        } else if (valorimc <= 35) {
            resultadoimc = "Con obesidad leve";
        } else if (valorimc <= 39) {
            resultadoimc = "Con obesidad media";
        } else {
            resultadoimc = "Con obesidad mórbida";
        }
        model.addAttribute("resultado","Su IMC es de: " +
                String.format("%.2f", valorimc)+", usted se encuentra: "+resultadoimc);
        model.addAttribute("verresultado", true);
        model.addAttribute("coloralert", coloralert);
        return "formImc";
    }
}
