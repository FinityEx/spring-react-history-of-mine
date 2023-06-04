import './App.css';
import './normalize.css'
import {Component} from "react";

import background from "./img/background.jpg"

class App extends Component{
  constructor() {
    super();

    this.state = {}
  }

  render() {
      const backgroundStyle={
      backgroundImage: `url(${background})`,
          backgroundRepeat:"no-repeat",
          backgroundSize:"cover",
          height:"100vh",
          width:"100vw",
          backgroundPosition:"center"
      }
    return (
        <div className="App" style={backgroundStyle}>

        </div>
    );
    }
}

export default App;
