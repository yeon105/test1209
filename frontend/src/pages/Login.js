import './App.css';
import {login, saveJwtToken, saveRole} from "../store";
import apiClient from "../api/axiosInstance";
import {useDispatch} from "react-redux";
import {useState} from "react";
import {useNavigate} from "react-router-dom";
import errorDisplay from "../util/errorDisplay";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const dispatch = useDispatch();
    const navigate =useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await apiClient.post("/login",
                new URLSearchParams({username, password}), {
                    withCredentials: true,
                });

            const token = response.headers["authorization"];
            console.log("jwt 토큰 : " + token);
            await dispatch(saveJwtToken(token));
            await dispatch(saveRole(response.data.role));
            // await dispatch(saveCsrfToken(response.data.token)); // state값을 바꿀 경우 await를 붙여서 비동기처리를 해준다.
            await dispatch(login());
            navigate("/");
        } catch (error) {
            errorDisplay(error);
        }
    };

    const handleClick=()=>{
        navigate("/register");
    }

    return <>
        <form onSubmit={handleSubmit}>
            <label htmlFor="userId">아이디: </label>
            <input type="text" value={username} onChange={e => setUsername(e.target.value)}
                   required></input>
            <br/>
            <br/>
            <label htmlFor="password">비밀번호: </label>
            <input type="password" value={password} onChange={e => setPassword(e.target.value)}
                   required></input>
            <br/>
            <br/>
            <button type="submit">로그인</button>
            <button onClick={handleClick}>회원가입</button>
        </form>
    </>;
}

export default Login;
