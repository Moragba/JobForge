import './App.css';

import { useState } from 'react';

import Welcome from './views/Welcome';
import Form from './views/Form';

//Auflistung der views
const welcomeView = 'welcome';
const formView = 'form';



export default function App() {
    //State für die aktuelle view
    const [view, setView] = useState(welcomeView);

    if(view === welcomeView) {
        return <Welcome onStart={() => setView(formView)} />
    }
    if(view === formView) {
        return <Form />
    }
}