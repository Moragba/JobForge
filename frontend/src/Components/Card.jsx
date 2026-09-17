import './Card.css';

export default function Card({
    cardTitle,
    cardContent,
    multipleFiles,
    fileName,
}) {    
    const handleFileChange = (event) => {
        const files = Array.from(event.target.files);

        //zum senden vorbereiten
        let formFiles = new FormData();
        for(const file of files){
            formFiles.append("data", file); 
        }

        // Senden an backend zur weiteren Validierung
        try{
            fetch("http://localhost:8090/api/upload", {
                method: "POST",
                body: formFiles
            }).then((response) =>{
                if(!response.ok){
                    console.log(response.status);
                }
                return response.text();               
            }).then((textData) => {
                if(textData.includes("success")){
                    //handle greenschift
                    // handle x button
                    //handling mit react states machen warscheinlich irghenwie classname oder so in dom manipulieren
                }
                if(textData.includes("success")){
                    //handle redshit and deactivation for 1 sekond
                }
                console.log(textData);                
            }).catch((ex) => {
                console.log(ex);
            })
        }catch (ex){
            console.log(ex)
        }
    }
    
    

    return(
        <div className="card">
            <div className="card-icon">
                <h3>{cardTitle}</h3>                
            </div>
            <div className="card-content">
                <p>{cardContent}</p>
                <input 
                type="file" 
                multiple={multipleFiles} 
                id={fileName} 
                name={fileName} 
                accept=".pdf,.doc,.docx"
                onChange={handleFileChange}
                />
            </div>
        </div>
    )
}