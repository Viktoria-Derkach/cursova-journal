import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="box">
        <h2>Авторизація</h2>
        <div className="inputBox">
            <input type="text" required="required"/>
            <label>Логін</label>
        </div>
        <div className="inputBox">
            <input type="password" required="required"/>
            <label>Пароль</label>
        </div>  
        <a href="#">Забули пароль?</a>
        <input type="submit" value="Увійти"/>
</div>
  );
}

export default App;