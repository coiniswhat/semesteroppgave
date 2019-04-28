 import java.io.File;                    
import java.io.FileInputStream;          
import java.io.FileOutputStream;         
import java.io.ObjectInputStream;        
import java.io.ObjectOutputStream;       
import java.io.IOException;              
 
public class Main {
 
    public static void main(String[] args) {
 
        // hver gang vi kjører program opprettes en ny objekt til klass Register. 
        Register e = new Register();
     

        // finn hvis det er en fil som lagrer objekt.
        if ( new File("./user-prefrences.jobj").exists() )
        {
            // hvis filen står i minne skal programer returnere status for objekt
            try
            {
                // her opprettes en objekt for å sette inn informasjon fra fil user-prefrences.jobj i minne
                
                FileInputStream fil = new FileInputStream("./user-prefrences.jobj");
                // her opprettes en objekt for å hente objekt fra klassen Register som ble lagt i filen user-prefrences.jobj 
                
                ObjectInputStream utfils = new ObjectInputStream(fil);
     
                 // her leses tilstand til objekt i minne som en objekt til klass Register og så lagres sin tilstand i objekt e.
   
                e = (Register) utfils.readObject();
                   
                // her kobles fra filen

                fil.close();
                utfils.close();
 
                // hvis det ikke er feil skal skrive ut i konsole følgende informasjon
                System.out.println("Deserialized data has been created in the memory");
                System.out.println("Language:      " + e.language);
                System.out.println("Encoding:      " + e.encoding);
                System.out.println("Font size:     " + e.fontSize);
                System.out.println("Font family:   " + e.fontFamily);
                System.out.println("Auto save:     " + e.autoSave);
            
                System.out.println("Auto Complete: " + e.autoComplete);
                System.out.println();
            }
            catch(IOException | ClassNotFoundException ex)
            {
                // hvis det er feil i henting av objekt 
                System.out.println(ex.getMessage());
            }
        }
 
 
 
 
        // for å bytte tilstand til objekt i ny fil 
        try
        {
            // her byttes dataene til objekt e 
            
            e.language   = "arabic";
            e.encoding   = "utf-8";
            e.fontSize   = "12pt";
            e.fontFamily = "tahoma";
            e.autoSave   = true;
    
           // her vi opprettes en ny fil 
            
            FileOutputStream fos = new FileOutputStream("./user-prefrences.jobj");
 
            // her opprettes en objekt for å hente tilstand til objekt som finnes i minne 
           
            ObjectOutputStream oos = new ObjectOutputStream(fos);
 
            // her kopires tilstand, til objekt, til filen for å legre endringer 
            
            oos.writeObject(e);
 
          // her kobles fra filen
            oos.close();
            fos.flush();
            fos.close();
 
           
            System.out.println("Serialized data ble lagret i prosjekt file user-prefrences.jobj");
        }
        catch(IOException ex)
        {
      // hvis det er feil i henting av objekt 
            System.out.println(ex.getMessage());
        }
 
    }
 
}

