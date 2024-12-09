import axios from "axios";
import store, {saveJwtToken} from "../store";

const apiClient = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        "Content-Type": "application/json",
    },
    // withCredentials: true
    // timeout: 3000,
});
// get, post, ... 등등 모든 요청은 비동기 요청으로 처리된다. 때문에 error 시, return은 Promise로 한다.
apiClient.interceptors.request.use((config) => {
    // 1. 요청 데이터가 URLSearchParams 타입인지 확인
    if (config.data instanceof URLSearchParams) {
        config.headers["Content-Type"] = "application/x-www-form-urlencoded";
    }

    // 2. 요청 메서드가 GET이 아닌 경우 CSRF 토큰 추가
    // if (config.method !== "get") {
    //     const csrfToken = store.getState().userInfo.csrfToken; // Redux 상태에서 csrfToken 가져오기
    //     config.headers["X-CSRF-TOKEN"] = csrfToken;
    // }

    const jwtToken = store.getState().post.jwtToken; // use로 시작하는 훅들은 컴포넌트안에서만 사용가능하다.
    config.headers["authorization"] = jwtToken;

    return config; // 수정된 config 반환
}, (error) => {
    // 요청을 가로채는 중에 에러 발생 시 처리
    return Promise.reject(error);
});

apiClient.interceptors.response.use((response) => response,
    async (error) => {
        const originalReq = error.config;
        if (error.response && error.response.status === 401 && !originalReq._retry) {
            originalReq._retry = true;

            try {
                const refreshResponse = await axios.post("http://localhost:8080/reissue",
                    null, {
                        withCredentials: true,
                    });
                const newAccessToken = refreshResponse.headers["authorization"];
                store.dispatch(saveJwtToken(newAccessToken));
                console.log("만료된 토큰 재발급 신청");

                return apiClient(originalReq);
            } catch (refreshError) {
                console.log("리프레쉬 토큰으로 재발급 실패");
                return Promise.reject(refreshError);
            }
            return Promise.reject(error);
        }
    });

export default apiClient;
