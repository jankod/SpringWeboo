let _lastPopover = null;

function showPopover(element, widgetInfo) {
    hidePopover(element);
    const popover = new bootstrap.Popover(element, {
        title: widgetInfo.widgetName,
        content: `<a class="btn btn-link" href="${widgetInfo.debugLink}" target="_blank"> ${widgetInfo.widgetId} </a>`,
        placement: 'right',
        trigger: 'manual',
        html: true,
       // delay: {show: 0, hide: 100},
    });
    _lastPopover = popover;
    popover.show();

}

function hidePopover(element) {
    if (_lastPopover) {
        _lastPopover.hide();
        _lastPopover = null;
    }
}


/**
 * @param widgetsInfo arrays of objects with id and name
 */
function addHoverEffect(widgetsInfo) {
    widgetsInfo.forEach(w => {
        console.log("Widget info", w);
        const element = document.getElementById(w.widgetId);
        if (!element) {
            console.error("Element with id " + w.widgetId + " not found");
            return;
        }
        if (element) {
            element.style.border = '2px solid transparent';
            element.style.transition = 'border 0.3s';

            element.addEventListener('mouseover', () => {
                element.style.border = '2px solid red';
                // showCustomDebugTooltip(element);
                showPopover(element, w);
            });

            element.addEventListener('mouseout', () => {
                element.style.border = '2px solid transparent';
                // hideCustomDebugTooltip();
                //  hidePopover(element);
            });
        }
    });
}


function initDebug() {
    if (WEBOO_WIDGETS_INFO) {
        addHoverEffect(WEBOO_WIDGETS_INFO);
        document.addEventListener('click', (event) => {
            if (_lastPopover && !event.target.closest('.popover') && !event.target.hasAttribute('id')) {
                _lastPopover.hide();
                _lastPopover = null;
            }
        });
    }

}

document.addEventListener('DOMContentLoaded', initDebug);