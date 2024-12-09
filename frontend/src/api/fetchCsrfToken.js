import axios from "axios";

const fetchCsrfToken=async ()=>{
    try{
        const response=await axios.get('http://localhost:8080/csrf-token', { withCredentials: true });
        console.log("토큰생성 성공: ", response.data.token);
        return response.data.token;
    }catch(error){
        console.log(error);
        console.log("csrf토큰 발급에러");
    }
}

export default fetchCsrfToken;