import './App.css';
import './normalize.css'

import React from 'react';
import SignIn from "./pages/SignIn"
import { Link } from 'react-router-dom';

const App = () => {
    const useRef = 0
    const useContext = 0;
    return (
            <SignIn />
        // <>
        //     <h1>React Router</h1>
        //
        //     <Navigation />
        //
        //     <Routes>
        //         <Route path="signin" element={<SignIn />} />
        //     </Routes>
        // </>
    );
};

// const Navigation = () => {
//     return (
//         <nav
//         >
//             <Link to="/signin">Home</Link>
//             {/*<Link to="/users">Users</Link>*/}
//         </nav>
//     );
// };
export default App;
