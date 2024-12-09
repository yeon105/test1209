import './App.css';
import Header from "./pages/Header";
import Login from "./pages/Login";
import {BrowserRouter, Routes, Route} from "react-router-dom";

function App() {

    return (
        <BrowserRouter>
            <Routes>
                <Route path={"/"} element={<Header/>}>
                    <Route index element={<Login/>}></Route>
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
