// TODO remove, this demo shouldn't need to reset the theme.
import {createTheme, ThemeProvider} from "@mui/material/styles";
import Container from "@mui/material/Container";
import Footer from "./home/Footer";
import * as React from "react";
import axios from "axios";
import TreeCompView from "../TreeCompView";

const defaultTheme = createTheme();
export default function SeeRelatives() {

    const handleRequest = (event) => {
        event.preventDefault();
        axios({
            method: "get",
            url: "/user/relative",
            headers: {"Content-Type": "application/json"},
        }).then((response) => {
            console.log(response);
        })
    }

    return (
        <ThemeProvider theme={defaultTheme}>
            <Container maxWidth="false">
                <TreeCompView />
            </Container>
            <Footer
                title="Footer"
                description="Something here to give the footer a purpose!"
            />
        </ThemeProvider>
    );
}
