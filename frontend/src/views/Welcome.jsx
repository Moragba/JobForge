
export default function Welcome({ onStart }) {
    return (
        <fieldset>
                <h1>Herzlich Willkommen bei JobForge</h1>
                <p>Dem Ki gestützten Bewerbungshelfer.</p>
                <br/>
                <p>Hier kannst du deine Bewerbungsanschreiben schnell und individuell erstellen.</p>
                <button onClick={onStart}>Jetzt starten</button>
            </fieldset>
    )
}