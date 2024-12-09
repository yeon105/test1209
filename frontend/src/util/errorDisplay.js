export default function errorDisplay(error) {
    if (error.code === "ECONNABORTED") {
        console.log("응답시간 초과");
    } else if (error.response) {
        console.log("서버에러");
        console.log(error.response.status);
        console.log(error.response.data);
        console.log(error);
    } else if (error.request) {
        console.log(error.message);
    } else {
        console.log(error.message);
    }
}