
const getFormattedDate = (milis: any) => {
    return new Date(Number.parseInt(milis)).toLocaleDateString();
}

const StringUtils = {
    getFormattedDate
}

export default StringUtils;