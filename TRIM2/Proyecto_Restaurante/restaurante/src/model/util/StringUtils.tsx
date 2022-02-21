const getFormattedDate = (milis: any) => {
    let dateObj = new Date(Number.parseInt(milis));
    let date = dateObj.toLocaleDateString();
    let time = dateObj.toLocaleTimeString().substring(0, 5);
    return date + " " + time;
}

const StringUtils = {
    getFormattedDate
}

export default StringUtils;