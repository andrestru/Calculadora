package calculadora.horas.applicationR.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Calculate {

    int normales=0, nocturnas=0, dominicales=0, normalesExtra=0, nocturnasExtra=0, dominicalesExtra=0, horasTotales=0;
    int minNor=0, minNoc=0, minDom=0, minNorEx=0, minNocEx=0, minDomEx=0;


    public Object[] calcularhoras(Object[] tecnico) {
        Object[] horas = new Object[6];
        Object[] aux = new Object[6];
        for (int i=0; i<6; i++){
            aux[i] = 0;
        }
        LocalDateTime fechaInicio, fechaFin;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Calendar numeroSemana= Calendar.getInstance();
        numeroSemana.setFirstDayOfWeek(Calendar.MONDAY);
        numeroSemana.setMinimalDaysInFirstWeek(2);
        String semana = "";
        for(int i=1; i< tecnico.length; i=i+2){
            fechaInicio = LocalDateTime.parse(tecnico[i].toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            fechaFin = LocalDateTime.parse(tecnico[i+1].toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            String dia = String.valueOf(fechaInicio.getDayOfWeek());
            try {
                numeroSemana.setTime(formatoFecha.parse(String.valueOf(fechaInicio)));
            } catch (ParseException e) {
                System.out.println("error "+e);
            }
            if((numeroSemana.get(numeroSemana.WEEK_OF_YEAR)-1) <10)
                semana = numeroSemana.get(numeroSemana.YEAR) + "-W0" + (numeroSemana.get(numeroSemana.WEEK_OF_YEAR) - 1);
            else
                semana = numeroSemana.get(numeroSemana.YEAR)+"-W"+(numeroSemana.get(numeroSemana.WEEK_OF_YEAR)-1);

            if(tecnico[0].toString().equals(semana) ){
                if(dia == "SUNDAY")
                    //Horas extra o no horas extra
                    if(horasTotales < 48) {
                        dominicales += fechaFin.getHour()-fechaInicio.getHour();
                        sundayLess48(fechaInicio, fechaFin);
                        //metodo 1!!!
                    }else{
                        //Metodo 2!!!
                        sundayHigher48(fechaInicio, fechaFin);
                    }
                else
                    //En este caso no son horas extra
                    if(horasTotales < 48) {
                        //Horas normales
                        if(fechaInicio.getHour() >= 7 && fechaInicio.getHour() < 20 && fechaFin.getHour() < 20) {
                            //Metodo 3!!!
                            notSundayLess48(fechaInicio, fechaFin);
                            //Horas nocturnas
                        }else if ( (fechaInicio.getHour() >= 20 || fechaInicio.getHour() < 7) && (fechaFin.getHour() >= 20 || fechaFin.getHour() < 7) ) {
                            //Metodo 4!!!
                            nightHoursLess48(fechaInicio, fechaFin);
                            //Empezo diurno y termino nocturno
                        }else if ( (fechaInicio.getHour() >= 7 && fechaInicio.getHour() < 20) && (fechaFin.getHour() >= 20 || fechaFin.getHour() < 7) ) {
                            //Metodo 5!!!
                            dayAndNightTurn(fechaInicio, fechaFin);
                            //Empezo nocturno y termino diurno
                        }else if ( (fechaInicio.getHour() >= 20 || fechaInicio.getHour() < 7) && (fechaFin.getHour() >= 7 && fechaFin.getHour() < 20) ) {
                            //Metodo 6!!!
                            nightAndDayTurn(fechaInicio, fechaFin);
                        }
                        //Lo mismo pero para las horas extra
                    }else {
                        //Horas normales extra
                        if(fechaInicio.getHour() >= 7 && fechaInicio.getHour() < 20 && fechaFin.getHour() < 20) {
                            //Metodo 7!!!
                            normalExtraHours(fechaInicio, fechaFin);
                            //Horas nocturnas
                        }else if ( (fechaInicio.getHour() >= 20 || fechaInicio.getHour() < 7) && (fechaFin.getHour() >= 20 || fechaFin.getHour() < 7) ) {
                            //Metodo 8!!!
                            nightHours(fechaInicio, fechaFin);
                            //Empezo diurno y termino nocturno
                        }else if ( (fechaInicio.getHour() >= 7 && fechaInicio.getHour() < 20) && (fechaFin.getHour() >= 20 || fechaFin.getHour() < 7) ) {
                            //Metodo 9!!!
                            starDateandFinishNight(fechaInicio, fechaFin);
                            //Empezo nocturno y termino diurno
                        }else if ( (fechaInicio.getHour() >= 20 || fechaInicio.getHour() < 7) && (fechaFin.getHour() >= 7 && fechaFin.getHour() < 20) ) {
                            startNightAndFinishDay(fechaInicio, fechaFin);
                        }
                    }

            }else
                return aux;
        }
        horas[0] = minNor<10 ? normales+":0"+minNor : normales+":"+minNor;
        horas[1] = minNoc<10 ? nocturnas+":0"+minNoc : nocturnas+":"+minNoc;
        horas[2] = minDom<10 ? dominicales+":0"+minDom : dominicales+":"+minDom;
        horas[3] = minNorEx<10 ? normalesExtra+":0"+minNorEx : normalesExtra+":"+minNorEx;
        horas[4] = minNocEx<10 ? nocturnasExtra+":0"+minNocEx : nocturnasExtra+":"+minNocEx;
        horas[5] = minDomEx<10 ? dominicalesExtra+":0"+minDomEx : dominicalesExtra+":"+minDomEx;
        return horas;
    }

    private int sundayLess48(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        if( fechaFin.getMinute() >= fechaInicio.getMinute() ){
            if( (minDom + (fechaFin.getMinute()-fechaInicio.getMinute())) >= 60 ){
                dominicales++;
                minDom = (minDom + (fechaFin.getMinute()-fechaInicio.getMinute())) - 60;
            }else
                minDom += fechaFin.getMinute()-fechaInicio.getMinute();
        }else {
            if( (minDom + (fechaInicio.getMinute() +60 -fechaFin.getMinute())) >= 60 ){
                dominicales++;
                minDom = (minDom + (fechaInicio.getMinute() +60 -fechaFin.getMinute())) - 60;
            }else
                minDom += fechaInicio.getMinute() +60 -fechaFin.getMinute();
        }
        horasTotales = normales+nocturnas+dominicales+normalesExtra+nocturnasExtra+dominicalesExtra+(int)((minNor+minNoc+minDom+minNorEx+minNocEx+minDomEx)/60);
        return horasTotales;
    }

    private int sundayHigher48(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        dominicalesExtra += fechaFin.getHour() - fechaInicio.getHour();
        if(fechaFin.getMinute() >= fechaInicio.getMinute()) {
            if( (minDomEx + (fechaFin.getMinute()-fechaInicio.getMinute())) >= 60 ){
                dominicalesExtra++;
                minDomEx = (minDomEx + (fechaFin.getMinute()-fechaInicio.getMinute()))-60;
            }else
                minDomEx += fechaFin.getMinute()-fechaInicio.getMinute();
        }else {
            if((minDomEx + (fechaInicio.getMinute() +60 -fechaFin.getMinute())) >= 60){
                dominicalesExtra++;
                minDomEx = (minDomEx + (fechaInicio.getMinute() +60 -fechaFin.getMinute())) - 60;
            }else
                minDomEx += fechaInicio.getMinute() +60 -fechaFin.getMinute();
        }
        horasTotales = normales+nocturnas+dominicales+normalesExtra+nocturnasExtra+dominicalesExtra+(int)((minNor+minNoc+minDom+minNorEx+minNocEx+minDomEx)/60);
        return horasTotales;
    }


    private int notSundayLess48(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        if(fechaFin.getMinute() >= fechaInicio.getMinute()) {
            normales += fechaFin.getHour()-fechaInicio.getHour();
            if((minNor + (fechaFin.getMinute()-fechaInicio.getMinute())) >= 60){
                normales++;
                minNor += (fechaFin.getMinute()-fechaInicio.getMinute()) - 60;
            }else
                minNor += (fechaFin.getMinute()-fechaInicio.getMinute());
        }else {
            normales += fechaFin.getHour()-fechaInicio.getHour()-1;
            if((minNor + (60 - (fechaInicio.getMinute() -fechaFin.getMinute()))) >= 60){
                normales++;
                minNor += (60 - (fechaInicio.getMinute() -fechaFin.getMinute())) - 60;
            }else
                minNor += (60 - (fechaInicio.getMinute() -fechaFin.getMinute()));
        }
        horasTotales = normales+nocturnas+dominicales+normalesExtra+nocturnasExtra+dominicalesExtra+(int)((minNor+minNoc+minDom+minNorEx+minNocEx+minDomEx)/60);
        return horasTotales;
    }

    private int nightHoursLess48(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        if(fechaInicio.getHour() >= 20 && fechaFin.getHour() <7)
            if(fechaFin.getMinute() >= fechaInicio.getMinute()) {
                nocturnas += 24-fechaInicio.getHour()+fechaFin.getHour();
                if((minNoc + (fechaFin.getMinute()-fechaInicio.getMinute())) >= 60){
                    nocturnas++;
                    minNoc += (fechaFin.getMinute()-fechaInicio.getMinute()) - 60;
                }else
                    minNoc += fechaFin.getMinute()-fechaInicio.getMinute();
            }else {
                nocturnas += 24-fechaInicio.getHour()+fechaFin.getHour()-1;
                if((minNoc + (60 - (fechaInicio.getMinute() -fechaFin.getMinute()))) >= 60){
                    nocturnas++;
                    minNoc += (60 - (fechaInicio.getMinute() -fechaFin.getMinute())) - 60;
                }else
                    minNoc += 60 - (fechaInicio.getMinute() -fechaFin.getMinute());
            }
        else
        if(fechaFin.getMinute() >= fechaInicio.getMinute()) {
            nocturnas += fechaFin.getHour()-fechaInicio.getHour();
            if((minNoc + (fechaFin.getMinute()-fechaInicio.getMinute())) >= 60){
                nocturnas++;
                minNoc += (fechaFin.getMinute()-fechaInicio.getMinute()) - 60;
            }else
                minNoc += fechaFin.getMinute()-fechaInicio.getMinute();
        }else {
            nocturnas += fechaFin.getHour()-fechaInicio.getHour()-1;
            if((minNoc + (60 - (fechaInicio.getMinute() -fechaFin.getMinute()))) >= 60){
                nocturnas++;
                minNoc += (60 - (fechaInicio.getMinute() -fechaFin.getMinute())) - 60;
            }else
                minNoc += 60 - (fechaInicio.getMinute() -fechaFin.getMinute());
        }
        horasTotales = normales+nocturnas+dominicales+normalesExtra+nocturnasExtra+dominicalesExtra+(int)((minNor+minNoc+minDom+minNorEx+minNocEx+minDomEx)/60);
        return horasTotales;
    }

    private int dayAndNightTurn(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        normales += 19-fechaInicio.getHour();
        if((minNor + (60-fechaInicio.getMinute())) >= 60){
            normales++;
            minNor =+ (60-fechaInicio.getMinute()) - 60;
        }else
            minNor += 60-fechaInicio.getMinute();
        if(fechaFin.getHour() >= 20) {
            nocturnas += (fechaFin.getHour()-20);
            if((minNoc + (fechaFin.getMinute())) >= 60){
                nocturnas++;
                minNoc += (fechaFin.getMinute()) - 60;
            }else
                minNoc += fechaFin.getMinute();
        }else {
            nocturnas += 4+fechaFin.getHour();
            if((minNoc + fechaFin.getMinute()) >= 60){
                nocturnas++;
                minNoc += fechaFin.getMinute() - 60;
            }else
                minNoc += fechaFin.getMinute();
        }
        horasTotales = normales+nocturnas+dominicales+normalesExtra+nocturnasExtra+dominicalesExtra+(int)((minNor+minNoc+minDom+minNorEx+minNocEx+minDomEx)/60);
        return horasTotales;
    }

    private int nightAndDayTurn(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        normales += fechaFin.getHour()-7;
        if((minNor + (fechaFin.getMinute())) >= 60){
            normales++;
            minNor += fechaFin.getMinute() - 60;
        }else
            minNor += fechaFin.getMinute();
        if(fechaInicio.getHour() >= 20)
            if(fechaFin.getMinute() >= fechaInicio.getMinute()) {
                nocturnas += 24-fechaInicio.getHour()+7;
                if((minNoc + (fechaInicio.getMinute())) >= 60){
                    nocturnas++;
                    minNoc += fechaInicio.getMinute() - 60;
                }else
                    minNoc += fechaInicio.getMinute();
            }else {
                nocturnas += 24-fechaInicio.getHour()+6;
                if((minNoc + (60 -fechaInicio.getMinute())) >= 60){
                    nocturnas++;
                    minNoc += (60 -fechaInicio.getMinute()) - 60;
                }else
                    minNoc += 60 -fechaInicio.getMinute();
            }
        else
        if(fechaFin.getMinute() >= fechaInicio.getMinute()) {
            nocturnas += 7-fechaInicio.getHour()-1;
            if((minNoc + (60 -fechaInicio.getMinute())) >= 60){
                nocturnas++;
                minNoc += (60 -fechaInicio.getMinute()) - 60;
            }else
                minNoc += 60 -fechaInicio.getMinute();
        }else {
            nocturnas += 7-fechaInicio.getHour();
            if((minNoc + (fechaInicio.getMinute())) >= 60){
                nocturnas++;
                minNoc += fechaInicio.getMinute() - 60;
            }else
                minNoc += fechaInicio.getMinute();
        }
        return horasTotales = normales+nocturnas+dominicales+normalesExtra+nocturnasExtra+dominicalesExtra+(int)((minNor+minNoc+minDom+minNorEx+minNocEx+minDomEx)/60);
    }

    private int normalExtraHours(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        if(fechaFin.getMinute() >= fechaInicio.getMinute()) {
            normalesExtra += fechaFin.getHour()-fechaInicio.getHour();
            if((minNorEx + (fechaFin.getMinute()-fechaInicio.getMinute())) >= 60){
                normalesExtra++;
                minNorEx += (fechaFin.getMinute()-fechaInicio.getMinute()) - 60;
            }else
                minNorEx += (fechaFin.getMinute()-fechaInicio.getMinute());
        }else {
            normalesExtra += fechaFin.getHour()-fechaInicio.getHour()-1;
            if((minNorEx + (60 - (fechaInicio.getMinute() -fechaFin.getMinute()))) >= 60){
                normalesExtra++;
                minNorEx += (60 - (fechaInicio.getMinute() -fechaFin.getMinute())) - 60;
            }else
                minNorEx += (60 - (fechaInicio.getMinute() -fechaFin.getMinute()));
        }
        return horasTotales = normales+nocturnas+dominicales+normalesExtra+nocturnasExtra+dominicalesExtra+(int)((minNor+minNoc+minDom+minNorEx+minNocEx+minDomEx)/60);
    }

    private int nightHours(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        if(fechaInicio.getHour() >= 20 && fechaFin.getHour() <7)
            if(fechaFin.getMinute() >= fechaInicio.getMinute()) {
                nocturnasExtra += 24-fechaInicio.getHour()+fechaFin.getHour();
                if((minNocEx + (fechaFin.getMinute()-fechaInicio.getMinute())) >= 60){
                    nocturnasExtra++;
                    minNocEx += (fechaFin.getMinute()-fechaInicio.getMinute()) - 60;
                }else
                    minNocEx += fechaFin.getMinute()-fechaInicio.getMinute();
            }else {
                nocturnasExtra += 24-fechaInicio.getHour()+fechaFin.getHour()-1;
                if((minNocEx + (60 - (fechaInicio.getMinute() -fechaFin.getMinute()))) >= 60){
                    nocturnasExtra++;
                    minNocEx += (60 - (fechaInicio.getMinute() -fechaFin.getMinute())) - 60;
                }else
                    minNocEx += 60 - (fechaInicio.getMinute() -fechaFin.getMinute());
            }
        else
        if(fechaFin.getMinute() >= fechaInicio.getMinute()) {
            nocturnasExtra += fechaFin.getHour()-fechaInicio.getHour();
            if((minNocEx + (fechaFin.getMinute()-fechaInicio.getMinute())) >= 60){
                nocturnasExtra++;
                minNocEx += (fechaFin.getMinute()-fechaInicio.getMinute()) - 60;
            }else
                minNocEx += fechaFin.getMinute()-fechaInicio.getMinute();
        }else {
            nocturnasExtra += fechaFin.getHour()-fechaInicio.getHour()-1;
            if((minNocEx + (60 - (fechaInicio.getMinute() -fechaFin.getMinute()))) >= 60){
                nocturnasExtra++;
                minNocEx += (60 - (fechaInicio.getMinute() -fechaFin.getMinute())) - 60;
            }else
                minNocEx += 60 - (fechaInicio.getMinute() -fechaFin.getMinute());
        }
        return horasTotales = normales+nocturnas+dominicales+normalesExtra+nocturnasExtra+dominicalesExtra+(int)((minNor+minNoc+minDom+minNorEx+minNocEx+minDomEx)/60);
    }

    private int starDateandFinishNight(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        normalesExtra += 19-fechaInicio.getHour();
        if((minNorEx + (60-fechaInicio.getMinute())) >= 60){
            normalesExtra++;
            minNorEx =+ (60-fechaInicio.getMinute()) - 60;
        }else
            minNorEx += 60-fechaInicio.getMinute();
        if(fechaFin.getHour() >= 20) {
            nocturnasExtra += (fechaFin.getHour()-20);
            if((minNocEx + (fechaFin.getMinute())) >= 60){
                nocturnasExtra++;
                minNocEx += (fechaFin.getMinute()) - 60;
            }else
                minNocEx += fechaFin.getMinute();
        }else {
            nocturnasExtra += 4+fechaFin.getHour();
            if((minNocEx + fechaFin.getMinute()) >= 60){
                nocturnasExtra++;
                minNocEx += fechaFin.getMinute() - 60;
            }else
                minNocEx += fechaFin.getMinute();
        }
        return horasTotales = normales+nocturnas+dominicales+normalesExtra+nocturnasExtra+dominicalesExtra+(int)((minNor+minNoc+minDom+minNorEx+minNocEx+minDomEx)/60);
    }

    private int startNightAndFinishDay(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        normalesExtra += fechaFin.getHour()-7;
        if((minNorEx + (fechaFin.getMinute())) >= 60){
            normalesExtra++;
            minNorEx += fechaFin.getMinute() - 60;
        }else
            minNorEx += fechaFin.getMinute();
        if(fechaInicio.getHour() >= 20)
            if(fechaFin.getMinute() >= fechaInicio.getMinute()) {
                nocturnasExtra += 24-fechaInicio.getHour()+7;
                if((minNocEx + (fechaInicio.getMinute())) >= 60){
                    nocturnasExtra++;
                    minNocEx += fechaInicio.getMinute() - 60;
                }else
                    minNocEx += fechaInicio.getMinute();
            }else {
                nocturnasExtra += 24-fechaInicio.getHour()+6;
                if((minNocEx + (60 -fechaInicio.getMinute())) >= 60){
                    nocturnasExtra++;
                    minNocEx += (60 -fechaInicio.getMinute()) - 60;
                }else
                    minNocEx += 60 -fechaInicio.getMinute();
            }
        else
        if(fechaFin.getMinute() >= fechaInicio.getMinute()) {
            nocturnasExtra += 7-fechaInicio.getHour()-1;
            if((minNocEx + (60 -fechaInicio.getMinute())) >= 60){
                nocturnasExtra++;
                minNocEx += (60 -fechaInicio.getMinute()) - 60;
            }else
                minNocEx += 60 -fechaInicio.getMinute();
        }else {
            nocturnasExtra += 7-fechaInicio.getHour();
            if((minNocEx + (fechaInicio.getMinute())) >= 60){
                nocturnasExtra++;
                minNocEx += fechaInicio.getMinute() - 60;
            }else
                minNocEx += fechaInicio.getMinute();
        }
        return horasTotales = normales+nocturnas+dominicales+normalesExtra+nocturnasExtra+dominicalesExtra+(int)((minNor+minNoc+minDom+minNorEx+minNocEx+minDomEx)/60);
    }






}

