import {useState, useEffect} from "react";
import './App.css';



function test() {
    const [backendResponse, setBackendResponse] = useState("Warte auf Antwort vom Backend...");

    fetch("http://localhost:8090/api/test")
        .then((response) => response.json())
        .then((data) => {
            setBackendResponse(data.message);
        })
        .catch((error) => {
            console.error("Fehler beim Abrufen der Backend-Antwort:", error);
            setBackendResponse("Fehler beim Abrufen der Backend-Antwort.");
        });
        return (
            <div>
                <h1>Testseite</h1>
                <p>{backendResponse}</p>
            </div>
        );
}



export default test;