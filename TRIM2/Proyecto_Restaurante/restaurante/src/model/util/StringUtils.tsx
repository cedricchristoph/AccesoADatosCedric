const getFormattedDate = (milis: any) => {
    let dateObj = new Date(Number.parseInt(milis));
    let date = dateObj.toLocaleDateString();
    let time = dateObj.toLocaleTimeString().substring(0, 5);
    return date + " " + time;
}

function formatDate(string: string){
    let options = { year: 'numeric', month: 'long', day: 'numeric' };
    return new Date(string).getTime();
}

const StringUtils = {
    getFormattedDate
}

export default StringUtils;