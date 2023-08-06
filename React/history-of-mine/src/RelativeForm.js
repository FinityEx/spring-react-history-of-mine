import * as React from 'react';
import {useState} from 'react';
import TextField from '@mui/material/TextField';
import MenuItem from '@mui/material/MenuItem';
import {DatePicker} from "@mui/lab";
import Grid from '@mui/material/Unstable_Grid2';


export default function RelativeFormComponent() {
    const [birthDateValue, setBirthDateValue] = useState(null);
    const [deathDateValue, setDeathDateValue] = useState(null);
    const [kinshipValue, setKinshipValue] = useState(null);
    const [sexValue, setSexValue] = useState(null);

    const sex = [
        {
            value: "MALE"
        },
        {
        value: "FEMALE"
        }
    ]

    const kinship = [

        {
            value: "Parent"
        },
        {
            value: "Child"
        },
        {
            value: "Sibling"
        },
        {
            value: "Spouse"
        }
    ]

    return (

            <Grid container rowSpacing={1} columnSpacing={{ xs: 1, sm: 2, md: 3 }}>
            <Grid xs={6}>
                <TextField
                    required
                    id="name"
                    label="Name"
                />
            </Grid>
            <Grid xs={6}>
                <TextField
                    required
                    id="last-name"
                    label="Last Name"
                />
            </Grid>
            <Grid xs={6}>
                <TextField
                    id="notes"
                    label="Personal notes"
                    multiline
                    maxRows={4}
                />
            </Grid>
            <Grid xs={6}>
                <DatePicker
                    label="Date of birth"
                    defaultValue={() => {new Date().toLocaleString()}}
                    value={birthDateValue}
                    onChange={(value) => setBirthDateValue(value)}
                    />
            </Grid>
            <Grid xs={6}>
                <DatePicker
                    label="Date of death"
                    defaultValue={() => {new Date().toLocaleString()}}
                    value={deathDateValue}
                    onChange={(value) => setDeathDateValue(value)}
                />
            </Grid>
            <Grid xs={6}>
                <TextField
                    id="sex"
                    select
                    label="Sex"
                    defaultValue=""
                >
                    {sex.map((option) => (
                        <MenuItem key={option.value}
                                  value={option.value}
                                  onChange={(value) => setSexValue(value)}>
                            {option.value}
                        </MenuItem>
                    ))}
                </TextField>
            </Grid>
            <Grid xs={6}>
                <TextField
                    id="kinship"
                    select
                    label="Kinship"
                    defaultValue=""
                >
                    {kinship.map((option) => (
                        <MenuItem key={option.value}
                                  value={option.value}
                                  onChange={(value) => setKinshipValue(value)}>
                            {option.value}
                        </MenuItem>
                    ))}
                </TextField>
            </Grid>
            </Grid>
    );
}