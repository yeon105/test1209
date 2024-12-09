import {createSlice, configureStore} from "@reduxjs/toolkit";

const postSlice = createSlice({
    name: "posts",
    initialState: {
        post: [],
        loginFlag: false,
        jwtToken: null,
        role: null,
    },
    reducers: {
        savePost: (state, action) => {
            state.post.push(action.payload);
        },
        deletePost: (state) => {
            state.post = [];
        },
        login: (state) => {
            state.loginFlag = true;
        },
        logout: (state) => {
            state.loginFlag = false;
            state.jwtToken = null;
        },
        saveJwtToken: (state, action) => {
            state.jwtToken = action.payload;
        },
        saveRole: (state, action) => {
            state.role = action.payload;
        }
    }
})

const store = configureStore({
    reducer: {
        posts: postSlice.reducer,
    }
})

export const {saveRole, savePost, deletePost, login, logout, saveJwtToken} = postSlice.actions;
export default store;