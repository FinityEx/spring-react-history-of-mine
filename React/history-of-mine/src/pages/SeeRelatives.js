// TODO remove, this demo shouldn't need to reset the theme.
import {createTheme, ThemeProvider} from "@mui/material/styles";
import * as React from "react";
import {useState} from "react";
import TreeCompView from "../TreeCompView";

const defaultTheme = createTheme();
export default function SeeRelatives() {
    const [relativesArray, setRelativesArray] = useState([]);
    let arr;

//     useEffect((e) => {
// }, []);

    return (
        <ThemeProvider theme={defaultTheme}>
        {/*<div>*/}
                <TreeCompView >
                    {arr}
                </TreeCompView>
        {/*</div>*/}
            {/*<Footer*/}
            {/*    title="Footer"*/}
            {/*    description="Something here to give the footer a purpose!"*/}
            {/*/>*/}
        </ThemeProvider>
    );
}
