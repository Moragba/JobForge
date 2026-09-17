import '../Styles/Form.css';
import Card from '../Components/Card';
export default function Form() {
    return (
        <>
            <fieldset>
                <h1>JobForge die JobSchmiede</h1>
                <span>Erstelle jetzt deine individuelle Bewerbungsanschreiben</span>
            </fieldset>   
            <fieldset className="form-fieldset">
                <h2>1. Deine Unterlagen</h2>
                <div className="card-grid">
                    <Card 
                    cardTitle="Anschreiben" 
                    cardContent="Lade dein Anschreiben hoch" 
                    fileName="anschreiben" />

                    <Card 
                    cardTitle="Lebenslauf" 
                    cardContent="Lade deinen Lebenslauf hoch" 
                    fileName="lebenslauf" />

                    <Card 
                    cardTitle="Zertifikate/Zeugnisse" 
                    cardContent="Lade deine Zertifikate und Zeugnisse hoch" 
                    fileName="zertifikate" 
                    multipleFiles={true} />

                    <Card 
                    cardTitle="Deine Skills" 
                    cardContent="Lade deine Skills hoch" 
                    fileName="skills" />
                </div>                
            </fieldset>
            <fieldset className="form-fieldset">
                <h2>2. Die Stellenausschreibung</h2>
            </fieldset>
            <fieldset className="form-fieldset">
                <h2>3. Deine Wünsche (optional)</h2>
            </fieldset>         
        </>      
    )
}
