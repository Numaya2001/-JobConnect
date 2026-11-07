import{createContext,useContext,useState}from'react';import api from'../api';
const C=createContext(null);
export function AuthProvider({children}){const[user,setUser]=useState(()=>{try{return JSON.parse(localStorage.getItem('jc_user'))}catch{return null}});
 async function login(email,password){const{data}=await api.post('/auth/login',{email,password});localStorage.setItem('jc_token',data.token);localStorage.setItem('jc_user',JSON.stringify(data.user));setUser(data.user);return data.user;}
 async function register(payload){return api.post('/auth/register',payload);}
 function logout(){localStorage.removeItem('jc_token');localStorage.removeItem('jc_user');setUser(null);}
 return <C.Provider value={{user,login,register,logout}}>{children}</C.Provider>}
export const useAuth=()=>useContext(C);
