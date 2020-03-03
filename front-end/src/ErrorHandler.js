export class ErrorHandler {
    static handleErrors(error) {
        var message;
        switch(error) {
            case 401: message = "Not authorized"; break;
            case 402: message = "Out of quota"; break;
            case 403: message = "No data"; break;
            case 400: message = "Bad request"; break;
            default: message =  error; break;
        }
        alert(message);
    }
}
