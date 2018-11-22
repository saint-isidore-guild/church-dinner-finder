import moment from 'moment'

export const formatStartAndEnd = (startTimestamp, endTimestamp) => {
    const startDate = moment.unix(startTimestamp)
    const endDate = moment.unix(endTimestamp)
    if (startDate.dayOfYear() === endDate.dayOfYear()) {
        return `${startDate.format('dddd, MMM D, YYYY [at] h:mm A').valueOf()} - ${endDate.format('h:mm A').valueOf()}`
    } else {
        return `${formatTimestamp(startTimestamp)} to ${formatTimestamp(endTimestamp)}`
    }
};

export const formatTimestamp = (timestamp) => {
    if (!timestamp) return ''
    return moment.unix(timestamp).format('MM/DD/YY hh:mm A').valueOf()
};
